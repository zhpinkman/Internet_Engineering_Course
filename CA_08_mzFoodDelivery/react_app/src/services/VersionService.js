import {BACKEND_VERSION_URL} from "../config/config";
import axios from 'axios';

export class VersionService {
    static async getBackendVersion() {
        try {
            let response = await axios.get(BACKEND_VERSION_URL);
            console.log(response);
            return response.data;
        } catch (error) {
            // handle error
            console.log(error);
            return "No response from server";
        }
    }
}