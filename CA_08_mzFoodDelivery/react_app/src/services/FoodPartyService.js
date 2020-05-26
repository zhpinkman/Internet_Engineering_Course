import {PARTY_FOODS_REMAINING_TIME_URL, PARTY_FOODS_URL} from "../config/config";
import { http } from './http'


export class FoodPartyService {
    static async getPartyFoods() {
        let obj = await http.get(PARTY_FOODS_URL);
        return obj.data;
    }
}

export class FoodPartyRemainingTimeService {
    static async getRemainingTime() {
        let obj = await http.get(PARTY_FOODS_REMAINING_TIME_URL);
        return obj.data;
    }
}