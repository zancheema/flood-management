import axios from "axios";
import { BASE_URL } from "../util/Common";

const client = axios.create({
    baseURL: `${BASE_URL}/auth`,
    timeout: 5 * 60 * 1000, // 5 minutes,
});

/**
 * 
 * @param {*} payload { usernmae: string, password: string, email: string, roles: [string] }
 */
export async function signUp(payload) {
    return await client.post('/signup', payload);
}

/**
 * 
 * @param {*} payload { username: string, password: string }
 */
export async function login(payload) {
    const { username, password, remember, role } = payload;
    const baseToken = btoa(`${username}:${password}`);
    return await client.post(`/login/${role}?remember=${remember}`, {}, {
        headers: {
            Authorization: `Basic ${baseToken}`
        }
    });
}

export async function resetPassword(payload) {
    return await client.post('/password/forgot', payload);
}
