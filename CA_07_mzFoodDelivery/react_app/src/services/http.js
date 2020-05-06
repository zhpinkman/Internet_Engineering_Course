import axios from 'axios';
import AuthService from "./AuthService";

let instance = axios.create();

let domain_url = "http://localhost:8080/";
let auth_url = "auth";
// let partyFoods_url = "partyFoods";
// let user_url = "user";
// let restaurants_url = "restaurants";
// let search_url = "search";

instance.interceptors.request.use((config) => {
    if (!config.url.startsWith(domain_url + auth_url)) {
        config.headers = {Authorization: AuthService.getAuthHeader()};
    }
    // axios.defaults.headers.common['Authorization'] = AuthService.getAuthHeader();
    return config;
})

instance.interceptors.response.use((response) => {
    if (response.status === 403) {

    }
    return response;
})

export const http = instance;