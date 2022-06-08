export class User {

    public firstName: string;
    public lastName: string;
    public email: string;
    public password: string;
    public password2: string;
    public captcha: string;

    constructor() {
        this.firstName = '';
        this.lastName = '';
        this.email = '';
        this.password = '';
        this.password2 = '';
        this.captcha = '';
    }

}
