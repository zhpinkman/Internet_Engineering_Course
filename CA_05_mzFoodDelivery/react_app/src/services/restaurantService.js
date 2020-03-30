import {RESTAURANTS_URL} from "../config/config";

const axios = require("axios").default;

export default function getRestaurants() {
    return axios.get(RESTAURANTS_URL);
};
