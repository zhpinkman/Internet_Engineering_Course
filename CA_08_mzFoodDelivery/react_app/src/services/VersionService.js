import {http} from "./http";
import {BACKEND_VERSION_URL} from "../config/config";

export class VersionService {
    static async getBackendVersion() {
        let obj = await http.get(BACKEND_VERSION_URL);
        return obj.data;
    }
}