import {RESTAURANTS_LIST_PAGE_SIZE, RESTAURANTS_URL, SEARCH_FOODS_URL, SEARCH_RESTAURANTS_URL} from "../config/config";
import AuthService from "./AuthService";

const axios = require("axios");
axios.defaults.headers.common['Authorization'] = AuthService.getAuthHeader();


export default class RestaurantService {

    static testGet() {
        console.log("zhivar")
        axios.get("http://localhost:8080/test").then(data => {
            console.log(data);
        })
    }

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