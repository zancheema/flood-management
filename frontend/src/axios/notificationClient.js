import axios from "axios";
import { BASE_URL } from "../util/Common";

const client = axios.create({
    baseURL: `${BASE_URL}/api/notifications`,
    timeout: 5 * 60 * 1000, // 5 minutes,
    headers: { "Authorization": `Bearer ${localStorage.getItem("access")}` }
});

/**
 * 
 * @param {*} payload { reportId: number, rescueTeamEmail: string, name: string, subject: string, message: string }
 */
export async function notifyRescueTeamMember(payload) {
    return await client.post('/new/rescue', payload);
}

export async function getAuthorityManagerNotifications() {
    return await client.get('/authority');
}

export async function getRescueTeamMemberNotifications() {
    return await client.get('/rescue');
}

export async function getRescueNotificationReport(rescueNotificationId) {
    return await client.get(`/rescue/${rescueNotificationId}/report`);
}
