import React, { Component } from 'react'
import { GoogleLogin, GoogleLogout } from 'react-google-login';
import AuthService from "../../services/AuthService";


const CLIENT_ID = '531123957595-6grppb7pa8usclorcp34vlc43dmhhedg.apps.googleusercontent.com';


class GoogleBtn extends Component {
    constructor(props) {
        super(props);

        this.state = {
            isLogined: false,
            accessToken: ''
        };

        this.login = this.login.bind(this);
        this.handleLoginFailure = this.handleLoginFailure.bind(this);
        this.logout = this.logout.bind(this);
        this.handleLogoutFailure = this.handleLogoutFailure.bind(this);
    }

    login (response) {
        let profile = response.getBasicProfile();
        console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
        console.log('Name: ' + profile.getName());
        console.log('Image URL: ' + profile.getImageUrl());
        console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
        var id_token = response.getAuthResponse().id_token;
        let userForm = {
            token: id_token
        }
        AuthService.googleLogin(userForm).then(data => {
            console.log(data.data);
            let bearerToken = data.data;
            let token = bearerToken.slice(7, bearerToken.length);
            console.log(token)
            localStorage.setItem("token", token);
            window.location = "/";
        })
    }

    logout (response) {
        this.setState(state => ({
            isLogined: false,
            accessToken: ''
        }));
    }

    handleLoginFailure (response) {
        alert('Failed to log in')
    }

    handleLogoutFailure (response) {
        console.log(response);
        alert('Failed to log out')
    }

    render() {
        return (
            <div className="d-flex justify-content-center align-items-center mt-5" dir="ltr">
                { this.state.isLogined ?
                    <GoogleLogout
                        clientId={CLIENT_ID}
                        buttonText='Logout'
                        onLogoutSuccess={ this.logout }
                        onFailure={ this.handleLogoutFailure }
                    >
                    </GoogleLogout>: <GoogleLogin
                        clientId={CLIENT_ID}
                        buttonText='Login'
                        onSuccess={ this.login }
                        onFailure={ this.handleLoginFailure }
                        cookiePolicy={ 'single_host_origin' }
                        responseType='code,token'
                    />
                }
            </div>
        )
    }
}

export default GoogleBtn;