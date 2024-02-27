import logo from "../assets/logo.webp";
import { CgProfile } from "react-icons/cg";
import { GiDeliveryDrone } from "react-icons/gi";
import "./Authority.css";
import "./AuthoritySendNotification.css";
import { Form } from "react-bootstrap";
import { useState } from "react";
import { Link, useParams } from "react-router-dom";
import { notifyRescueTeamMember } from "../axios/notificationClient";
import ProfileButton from "../components/ProfileButton";

function AuthoritySendNotification() {
    const { reportId } = useParams();
    const [notification, setNotification] = useState({reportId: reportId});

    function handleInputChange(e) {
        setNotification({...notification, [e.target.name]: e.target.value});
    }

    async function handleNotificationSend() {
        try {
            await notifyRescueTeamMember(notification);
            alert("Notification sent successfully.");
            window.location.reload();
        } catch (e) {
            alert("Failed to send notification. Please check the fields.");
        }
    }

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
            </header>
            <section className="m-4 rounded card-interior text-light p-4 scrollable d-flex justify-content-end" style={{maxHeight: '470px'}}>
                <div style={{width: '50%'}}>
                    <div className="send-notification-header p-2">
                        <h4>Send Notification</h4>
                    </div>
                    <Form>
                        <Form.Group className="mt-3">
                            <Form.Label>Name: </Form.Label>
                            <Form.Control name="name" placeholder="Enter Name"
                            value={notification.name} onChange={handleInputChange} />
                        </Form.Group>
                        <Form.Group className="mt-3">
                            <Form.Label>Email: </Form.Label>
                            <Form.Control name="rescueTeamEmail" placeholder="Enter Email"
                            value={notification.rescueTeamEmail} onChange={handleInputChange} />
                        </Form.Group>
                        <Form.Group className="mt-3">
                            <Form.Label>Subject: </Form.Label>
                            <Form.Control name="subject" placeholder="Enter Subject"
                            value={notification.subject} onChange={handleInputChange} />
                        </Form.Group>
                        <Form.Group className="mt-3">
                            <Form.Label>Message: </Form.Label>
                            <Form.Control as="textarea" rows={4} name="message" placeholder="Enter Message"
                            value={notification.message} onChange={handleInputChange} />
                        </Form.Group>
                    </Form>

                </div>
                <div className="d-flex flex-column-reverse">
                    <button className="rounded-pill" id="submit-button" onClick={handleNotificationSend}>
                        Submit
                    </button>
                </div>
            </section>
        </div>
    );
}

export default AuthoritySendNotification;
