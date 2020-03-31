import {PARTY_FOODS_URL} from "../config/config";

const axios = require("axios").default;

export default class FoodPartyService {
    static async getPartyFoods() {
        let obj = await axios.get(PARTY_FOODS_URL);
        return obj.data;
    }
}