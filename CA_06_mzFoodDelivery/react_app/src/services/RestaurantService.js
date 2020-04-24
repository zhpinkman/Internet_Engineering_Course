import {RESTAURANTS_LIST_PAGE_SIZE, RESTAURANTS_URL, SEARCH_FOODS_URL, SEARCH_RESTAURANTS_URL} from "../config/config";

const axios = require("axios").default;

// export default function getRestaurants() {
//     return axios.get(RESTAURANTS_URL);
// };
//
// export default function getUser() {
//     return axios.get(USER_URL);
// };



export default class RestaurantService {
    static generatePageQuery(page) {
        return "?pageNumber=" + page + "&pageSize=" + RESTAURANTS_LIST_PAGE_SIZE;
    }

    static async getRestaurants(page) {
        let restaurants = await axios.get(RESTAURANTS_URL + RestaurantService.generatePageQuery(page));
        console.log(restaurants);
        return restaurants.data;
    }

    static async searchRestaurants(searchPhrase, page) {
        let restaurants = await axios.get(SEARCH_RESTAURANTS_URL + RestaurantService.generatePageQuery(page) + "&q=" + searchPhrase);
        console.log(restaurants);
        return restaurants.data;
    }

    static async searchFoods(searchPhrase, page) {
        let restaurants = await axios.get(SEARCH_FOODS_URL + RestaurantService.generatePageQuery(page) + "&q=" + searchPhrase);
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