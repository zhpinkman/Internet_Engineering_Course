import {RESTAURANTS_URL} from "../config/config";

const axios = require("axios").default;

// export default function getRestaurants() {
//     return axios.get(RESTAURANTS_URL);
// };
//
// export default function getUser() {
//     return axios.get(USER_URL);
// };



export default class RestaurantService {

    static getRestaurants() {
        return axios.get(RESTAURANTS_URL);
    }

}