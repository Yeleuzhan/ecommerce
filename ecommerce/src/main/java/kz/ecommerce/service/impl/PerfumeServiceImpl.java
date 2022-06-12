package kz.ecommerce.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import kz.ecommerce.domain.Perfume;
import kz.ecommerce.exception.ApiRequestException;
import kz.ecommerce.repository.PerfumeRepository;
import kz.ecommerce.service.PerfumeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class PerfumeServiceImpl implements PerfumeService {

    private final PerfumeRepository perfumeRepository;
    private final AmazonS3 amazonS3Client;

    @Value("${amazon.s3.bucket-name}")
    private String bucketName;

    public PerfumeServiceImpl(PerfumeRepository perfumeRepository, AmazonS3 amazonS3Client) {
        this.perfumeRepository = perfumeRepository;
        this.amazonS3Client = amazonS3Client;
    }

    @Override
    @Transactional
    public Perfume savePerfume(Perfume perfume, MultipartFile multipartFile) {
        if (multipartFile == null) {
            perfume.setFilename(amazonS3Client.getUrl(bucketName, "empty.jpg").toString());
        } else {
            File file = new File(multipartFile.getOriginalFilename());
            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                fileOutputStream.write(multipartFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            String fileName = UUID.randomUUID() + "." + multipartFile.getOriginalFilename();
            amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, file));
            perfume.setFilename(amazonS3Client.getUrl(bucketName, fileName).toString());
            file.delete();
        }
        return perfumeRepository.save(perfume);
    }

    @Override
    public List<Perfume> getAllPerfumes() {
        return perfumeRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Perfume getPerfumeById(Long perfumeId) {
        return perfumeRepository.findById(perfumeId)
                .orElseThrow(() -> new ApiRequestException("Perfume not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Perfume> getPerfumesByIds(List<Long> perfumesId) {
        return perfumeRepository.findByIdIn(perfumesId);
    }

    @Override
    public List<Perfume> findPerfumesByFilterParams(List<String> perfumers, List<String> genders, List<Integer> prices, boolean sortByPrice) {
        return perfumeRepository.findPerfumesByFilterParams(perfumers, genders, prices.get(0), prices.get(1), sortByPrice);
    }

    @Override
    public List<Perfume> findByPerfumeGender(String perfumeGender) {
        return perfumeRepository.findByPerfumeGenderOrderByPriceDesc(perfumeGender);
    }

    @Override
    public List<Perfume> findByPerfumer(String perfumer) {
        return perfumeRepository.findByPerfumerOrderByPriceDesc(perfumer);
    }

    @Override
    @Transactional
    public String deletePerfume(Long perfumeId) {
        Perfume perfume = perfumeRepository.findById(perfumeId)
                .orElseThrow(() -> new ApiRequestException("Perfume not found.", HttpStatus.NOT_FOUND));
        perfumeRepository.delete(perfume);
        return "Perfume deleted successfully";
    }

}
