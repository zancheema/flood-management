import axios from "axios";
import { BASE_URL } from "../util/Common";

const client = axios.create({
    baseURL: `${BASE_URL}/api/public/drones`,
    timeout: 5 * 60 * 1000, // 5 minutes,
    headers: { "Authorization": `Bearer ${localStorage.getItem("access")}` }
});

export async function getAllDrones() {
    return await client.get('/all');
}
