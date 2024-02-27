import { useEffect, useState } from "react";
import { getAuthorityManagerNotifications } from "../axios/notificationClient";

export function useAuth() {
    const [isAuthenticated, setIsAuthenticated] = useState({ authenticated: false });

    useEffect(() => {
        (async () => {
            try {
                await getAuthorityManagerNotifications();
                console.log('getAuthorities success (useAuth)')
                setIsAuthenticated({ 
                    authenticated: true, 
                    role: localStorage.getItem('role') 
                });
            } catch (e) {
                console.log('getAuthorities failure (useAuth)')
                setIsAuthenticated({ authenticated: false });
            }
        })();
    }, []);

    useEffect(() => {
        console.log('isAuthenticated: ' + JSON.stringify(isAuthenticated));
    }, [isAuthenticated]);

    return [isAuthenticated, setIsAuthenticated];
}
