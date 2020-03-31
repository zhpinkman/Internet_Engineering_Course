import {CART_URL, CHARGE_URL, ORDERS_URL, USER_URL} from "../config/config";

const axios = require("axios").default;


export default class UserService {

    static getUser() {
        return axios.get(USER_URL);
    }


    static charge(amount) {
        return axios.post(CHARGE_URL, {amount: amount});
    }

    static getCart() {
        return axios.get(CART_URL);
    }

    static getOrders() {
        return axios.get(ORDERS_URL);
    }

}