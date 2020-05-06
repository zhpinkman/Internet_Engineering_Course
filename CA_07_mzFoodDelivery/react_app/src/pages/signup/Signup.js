import "../../Assets/styles/signup-styles.css";

import * as React from "react";
import {Link} from "react-router-dom";
import AuthService from "../../services/AuthService";

export default class Signup extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            firstName: "",
            lastName: "",
            email: "",
            password: "",
            repeatPassword: ""
        }

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.validateForm = this.validateForm.bind(this);
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        this.setState({ [name]: value });
    }

    handleSubmit(event) {
        event.preventDefault();
        let userForm = {
            email: this.state.email,
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            password: this.state.password
        }
        AuthService.signup(userForm).then(data => {
            console.log(data.data);
            let bearerToken = data.data;
            let token = bearerToken.slice(7, bearerToken.length);
            console.log(token)
            localStorage.setItem("token", token);
            window.location = "/";
        })
    }

    componentDidMount() {
        document.title = "Create your MzFood Account";
    }

    validateForm() {
        return (
            this.state.firstName.length > 0 &&
                this.state.lastName.length > 0 &&
                this.state.email.length > 0 &&
                this.state.password.length > 0 &&
                this.state.repeatPassword.length > 0 &&
                this.state.password === this.state.repeatPassword
        );
    }

    render() {
        return (
            <div className="main-box w-100 h-100">
                <div className="d-flex flex-row-reverse no-gutters h-100">
                    <div className="col-lg-9 col-md-8 h-100">
                        <img className="background-img" src={require("../../Assets/images/food.jpg")} alt=""/>
                    </div>
                    <div className="col-lg-3 col-md-4 overflow-auto">
                        <div className="form-container">
                            <div className="d-flex">
                                <h1>
                                    ثبت‌نام
                                </h1>
                            </div>
                            <div className="d-flex justify-content-center align-items-center ">
                                <form action="tmp" className=" w-100" onSubmit={this.handleSubmit} onChange={this.handleChange}>
                                    <div className="">
                                        <div className="form-item">
                                            <div className="d-flex">
                                                <label htmlFor="firstName">
                                                    نام
                                                </label>
                                            </div>
                                            <input type="text" name="firstName" id="firstName" placeholder="نام"/>
                                        </div>
                                        <div className="form-item">
                                            <div className="d-flex">
                                                <label htmlFor="lastName">
                                                    نام خانوادگی
                                                </label>
                                            </div>
                                            <input type="text" name="lastName" id="lastName" placeholder="نام خانوادگی"/>
                                        </div>
                                        <div className="form-item">
                                            <div className="d-flex">
                                                <label htmlFor="email">
                                                    ایمیل
                                                </label>
                                            </div>
                                            <input type="text" name="email" id="email" placeholder="ایمیل"/>
                                        </div>
                                        <div className="form-item">
                                            <div className="d-flex">
                                                <label htmlFor="password">
                                                    رمز عبور
                                                </label>
                                            </div>
                                            <input type="password" name="password" id="password"
                                                   placeholder="***********"/>
                                        </div>
                                        <div className="form-item">
                                            <div className="d-flex">
                                                <label htmlFor="repeatPassword">
                                                    تکرار رمز عبور
                                                </label>
                                            </div>
                                            <input type="password" name="repeatPassword" id="repeatPassword"
                                                   placeholder="***********"/>
                                        </div>
                                        <div className="form-submit row">
                                            <div className="col-auto">

                                                <input type="submit" disabled={!this.validateForm()} value="ثبت‌نام"/>
                                            </div>
                                            <div
                                                className="col-auto d-flex justify-content-center align-items-center clickable">
                                                <div className="login-arrow">
                                                    <i className="flaticon-arrow"></i>
                                                </div>
                                                <span>
                                                    <Link to="/login">
                                                         ورود
                                                    </Link>
                                                </span>
                                            </div>
                                        </div>
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}