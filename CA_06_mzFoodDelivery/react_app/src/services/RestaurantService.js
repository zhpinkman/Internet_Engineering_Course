import {RESTAURANTS_LIST_PAGE_SIZE, RESTAURANTS_URL} from "../config/config";

const axios = require("axios").default;

// export default function getRestaurants() {
//     return axios.get(RESTAURANTS_URL);
// };
//
// export default function getUser() {
//     return axios.get(USER_URL);
// };



export default class RestaurantService {

    static async getRestaurants(page) {
        let restaurants = await axios.get(RESTAURANTS_URL + "?pageNumber=" + page + "&pageSize=" + RESTAURANTS_LIST_PAGE_SIZE);
        console.log(restaurants);
        return restaurants.data;
    }

    static async getRestaurantById(id) {
        let restaurant = await axios.get(RESTAURANTS_URL + id);
        if(restaurant.data !== "")
            return restaurant.data;
        else
            return null;
    }

}