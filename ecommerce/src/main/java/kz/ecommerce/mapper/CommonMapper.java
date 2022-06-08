package kz.ecommerce.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommonMapper {

    private final ModelMapper modelMapper;

    public CommonMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    <T, S> S convertToEntity(T data, Class<S> type) {
        return modelMapper.map(data, type);
    }

    <T, S> S convertToResponse(T data, Class<S> type) {
        return modelMapper.map(data, type);
    }

    <T, S> List<S> convertToResponseList(List<T> list, Class<S> type) {
        return list.stream()
                .map(item -> convertToResponse(item, type))
                .collect(Collectors.toList());
    }

}
