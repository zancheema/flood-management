import logo from "../assets/logo.webp";
import { CgProfile } from "react-icons/cg";
import { FcManager } from "react-icons/fc";
import "./RescueTeam.css";
import { useEffect, useState } from "react";
import { getRescueTeamMemberNotifications } from "../axios/notificationClient";
import { useNavigate } from "react-router-dom";
import ProfileButton from "../components/ProfileButton";

function RescueTeam() {
    const [notifications, setNotifications] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        getRescueNotifications();
    }, []);

    async function getRescueNotifications() {
        const notifications = (await getRescueTeamMemberNotifications()).data;
        setNotifications(notifications);
    }

    function openRescueNotificationReport(notificationId) {
        navigate(`/rescue/${notificationId}/report`);
    }

    return (
        <div className="RescueTeam">
        
            <header className="d-flex justify-content-between">
                <img className="logo" src={logo} alt="Logo" />
                <nav className="vertical-center">
                    <h4 style={{marginBottom: '0'}}><strong>Notification</strong></h4>
                </nav>
                <ProfileButton title={'rescue team'} />
            </header>
            <section className="m-4 rounded card-interior text-light p-4 scrollable" style={{maxHeight: '470px'}}>
                {
                    notifications.map(notification => (
                        <div className="ps-4 pe-4 pt-1 pb-1 mb-4 rounded-pill d-flex justify-content-between bg-light-blue" style={{width: '100%'}}>
                            <div className="d-flex">
                                <FcManager style={{height: '70px', width: 'auto'}} className="vertical-center me-3" />
                                <div className="vertical-center" style={{textAlign: 'center', height: 'fit-content'}}>
                                    <h4>Report from Manager {notification.authorityManagerId}</h4>
                                    <p>{notification.time}</p>
                                </div>
                            </div>
                            <button 
                                className="tx-purple vertical-center rounded-pill ps-4 pe-4 pt-1 pb-1" 
                                style={{height: 'fit-content'}}
                                onClick={() => openRescueNotificationReport(notification.notificationId)}>
                                    View
                            </button>
                        </div>
                    ))
                }
            </section>
        </div>
    );
}

export default RescueTeam;
