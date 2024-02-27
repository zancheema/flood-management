import logo from "../assets/logo.webp";
import { CgProfile } from "react-icons/cg";
import { FaLocationDot } from "react-icons/fa6";
import "./Authority.css";
import "./AuthoritySendNotification.css";
import { Col, Container, Row } from "react-bootstrap";
import { useEffect, useState } from "react";
import Map from "./Map";
import { Link } from "react-router-dom";
import { getAllDrones } from "../axios/droneClient";
import ProfileButton from "../components/ProfileButton";

function AuthorityLocation() {

    const [drones, setDrones] = useState([]);

    useEffect(() => {
        getDrones();
    }, []);

    async function getDrones() {
        const drones = (await getAllDrones()).data;
        setDrones(drones);
    }

    return (
        <div className="RescueTeam">
        
            <header className="d-flex justify-content-between">
                <img className="logo" src={logo} alt="Logo" />
                <nav className="vertical-center d-flex">
                    <h4 style={{marginBottom: '0'}} className="me-3">Report</h4>
                    <Link to={'/authority/notification'} style={{textDecoration: 'none', color: 'white'}}>
                        <h5 style={{marginBottom: '0'}} className="me-3">Notification</h5>
                    </Link>
                    <h5 style={{marginBottom: '0'}}><strong>Location</strong></h5>
                </nav>
                <ProfileButton title={'Authority manager'} />
            </header>
            <section className="m-4 rounded card-interior text-light p-4 scrollable d-flex justify-content-end" style={{maxHeight: '470px'}}>
                <Container>
                    <Row>
                        <Col md={6}>
                            <div className="p5">
                                <h5>Results</h5>
                                <p>Check out the drones in different areas</p>
                            </div>
                            <div className="bg-light text-dark p-2 m-4">
                                {
                                    drones.map(drone => (
                                        <div className="d-flex">
                                            <FaLocationDot style={{height: '30px'}} className="me-2" />
                                            <div style={{width: '100%'}}>
                                                <strong>Drone {drone.droneId}</strong>
                                                <div>Location: {`${drone?.location?.lat ?? ''} LAT, ${drone?.location?.lng ?? ''} LNG`}</div>
                                                <div className="drone-divider"></div>
                                            </div>
                                        </div>
                                    ))
                                }
                            </div>
                        </Col>
                        <Col md={6}>
                            <div className="scrollable">
                                <Map drones={drones} />
                            </div>
                        </Col>
                    </Row>
                </Container>
            </section>
        </div>
    );
}

export default AuthorityLocation;
