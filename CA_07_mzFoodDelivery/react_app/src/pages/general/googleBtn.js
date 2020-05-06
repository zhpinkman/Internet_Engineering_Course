import React, { Component } from 'react'
import { GoogleLogin, GoogleLogout } from 'react-google-login';


const CLIENT_ID = '584002652429-182cdq0g70sbla6gg58jmtqnsodflk97.apps.googleusercontent.com';


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
        alert('Failed to log out')
    }

    render() {
        return (
            <div>
                { this.state.isLogined ?
                    <GoogleLogout
                        clientId={ CLIENT_ID }
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
                { this.state.accessToken ? <h5>Your Access Token: <br/><br/> { this.state.accessToken }</h5> : null }

            </div>
        )
    }
}

export default GoogleBtn;