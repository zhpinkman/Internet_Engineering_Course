import {CART_URL, CHARGE_URL, ORDERS_URL, RESTAURANTS_URL, USER_URL} from "../config/config";

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

    static async addToCart(restaurantId, foodName, amount = 1) {
        await axios.post(CART_URL, {restaurantId: restaurantId, foodName: foodName, amount: amount})
            .then(function (response) {
                if (response.data !== "")
                    return response.data;
            })
            .catch(function (error) {
                console.log(error);
                return null;
            });
    }

}