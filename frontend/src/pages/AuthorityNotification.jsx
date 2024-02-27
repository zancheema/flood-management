import logo from "../assets/logo.webp";
import { CgProfile } from "react-icons/cg";
import { GiDeliveryDrone } from "react-icons/gi";
import "./Authority.css";
import { useEffect, useState } from "react";
import { getAuthorityManagerNotifications } from "../axios/notificationClient";
import { Link, useNavigate } from "react-router-dom";
import ProfileButton from "../components/ProfileButton";

function AuthorityNotification() {
    const [notifications, setNotifications] = useState([]);
    const navigate = useNavigate();

    async function getNotifications() {
        const notifications = (await getAuthorityManagerNotifications()).data;
        setNotifications(notifications);
    }

    function openReport(reportId) {
        navigate(`/authority/report/${reportId}`);
    }

    useEffect(() => {
        getNotifications();
    }, []);

    return (
        <div className="RescueTeam">
        
            <header className="d-flex justify-content-between">
                <img className="logo" src={logo} alt="Logo" />
                <nav className="vertical-center d-flex">
                    <h5 style={{marginBottom: '0'}} className="me-3">Report</h5>
                    <h4 style={{marginBottom: '0'}} className="me-3"><strong>Notification</strong></h4>
                    <Link to={'/authority/location'} style={{textDecoration: 'none', color: 'white'}}>
                        <h5 style={{marginBottom: '0'}}>Location</h5>
                    </Link>
                </nav>
                <ProfileButton title={'Authority manager'} />
                {/* <button style={{height: 'fit-content'}} className="bg-light pt-1 pb-1 ps-3 pe-3 rounded-pill vertical-center me-3">
                    <CgProfile className="me-3 vertical-center" style={{height: '20px', width: 'auto'}} />
                    <span>Authority manager</span>
                </button> */}
            </header>
            <section className="m-4 rounded card-interior text-light p-4 scrollable" style={{maxHeight: '470px'}}>
                {
                    notifications.map(notification => (
                        <div className="ps-4 pe-4 pt-1 pb-1 mb-4 rounded-pill d-flex justify-content-between bg-light-blue" style={{width: '100%'}}>
                            <div className="d-flex">
                                <GiDeliveryDrone style={{height: '70px', width: 'auto'}} className="vertical-center me-3" />
                                <div className="vertical-center" style={{textAlign: 'center', height: 'fit-content'}}>
                                    <h4>Report from Drone {notification.droneId}</h4>
                                    <p>{notification.time}</p>
                                </div>
                            </div>
                            <button 
                                className="tx-purple vertical-center rounded-pill ps-4 pe-4 pt-1 pb-1" 
                                style={{height: 'fit-content'}}
                                onClick={() => openReport(notification.reportId)}>
                                    View
                            </button>
                        </div>
                    ))
                }
            </section>
        </div>
    );
}

export default AuthorityNotification;
