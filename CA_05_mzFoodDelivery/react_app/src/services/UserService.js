import {CART_URL, CHARGE_URL, ORDERS_URL, USER_URL} from "../config/config";
import Translator from "../utils/Translator";

const axios = require("axios").default;


export default class UserService {

    static getUser() {
        return axios.get(USER_URL);
    }


    static charge(amount) {
        return axios.post(CHARGE_URL, {amount: amount});
    }

    static async getCart() {
        return await axios.get(CART_URL);
    }

    static getOrders() {
        return axios.get(ORDERS_URL);
    }

    static async addToCart(restaurantId, foodName, amount = 1) {
        try {
            let response = await axios.post(CART_URL, {restaurantId: restaurantId, foodName: foodName, amount: amount})
            if (response.data !== "" || response.data === undefined)
                return Translator.toFa(response.data);
            else
                return "مشکلی پیش آمده! لطفا دوباره تلاش کنید";
        }catch (e) {
            return "مشکلی پیش آمده! لطفا دوباره تلاش کنید";
            // return e.toString();
        }
    }

    static async removeFromCart(restaurantId, foodName) {
        try {
            let response = await axios.delete(CART_URL, {data: {restaurantId: restaurantId, foodName: foodName}})
            if (response.data !== "" || response.data === undefined)
                return Translator.toFa(response.data);
            else
                return "مشکلی پیش آمده! لطفا دوباره تلاش کنید";
        }catch (e) {
            return "مشکلی پیش آمده! لطفا دوباره تلاش کنید";
            // return e.toString();
        }
    }

    static async finalizeOrder() {
        try {
            let response = await axios.post(ORDERS_URL);
            if (response.data !== "" || response.data === undefined)
                return Translator.toFa(response.data);
            else
                return "مشکلی پیش آمده! لطفا دوباره تلاش کنید";
        }catch (e) {
            return "مشکلی پیش آمده! لطفا دوباره تلاش کنید";
            // return e.toString();
        }
    }

}