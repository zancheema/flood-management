import logo from "../assets/logo.webp";
import { CgProfile } from "react-icons/cg";
import "./Authority.css";
import "./AuthoritySendNotification.css";
import "./AuthorityReport.css";
import { useParams } from "react-router-dom";
import { useState, useEffect } from "react";
import { BASE_URL } from "../util/Common";
import { getRescueNotificationReport } from "../axios/notificationClient";
import ProfileButton from "../components/ProfileButton";

function RescueReport() {
    const { rescueNotificationId } = useParams();

    const [report, setReport] = useState({});

    useEffect(() => {
        console.log('rescueNotificationId: ' + rescueNotificationId);
        getReport();
    }, []);

    async function getReport() {
        const report = (await getRescueNotificationReport(rescueNotificationId)).data;
        setReport(report);
    }

    return (
        <div className="RescueTeam">
        
            <header className="d-flex justify-content-between">
                <img className="logo" src={logo} alt="Logo" />
                <nav className="vertical-center d-flex">
                <   h4 style={{marginBottom: '0'}}><strong>Notification</strong></h4>
                </nav>
                <ProfileButton title={'rescue team'} />
            </header>
            <section className="m-4 rounded card-interior text-light p-4 scrollable d-flex justify-content-center" style={{maxHeight: '470px'}}>
                <div style={{width: '50%'}} className="me-5">
                    <div className="lighter-card p-2 rounded" >
                        <h5>From: Manager {report.managerId}</h5>
                        <h5>Subject: {report.subject}</h5>
                        <div>
                            <h5>Message:</h5>
                            <p className="ms-4">{report.message}</p>
                        </div>
                        {/* <h5>Report ID: {report.reportId}</h5> */}
                        <h5>Date: {report.date}</h5>
                        <h5>Time: {report.time}</h5>
                        <div className="d-flex">
                            <h5>Mission Location: </h5>
                            <a 
                                href={`https://maps.google.com/?q=${report?.location?.lat ?? ''},${report?.location?.lng ?? ''}`} 
                                target="_blank"
                                style={{textDecoration: 'none'}}>
                                <div 
                                    className="tx-purple vertical-center rounded-pill ps-4 pe-4 ms-4" 
                                    style={{height: 'fit-content', backgroundColor: 'white', color: 'black'}}>
                                        View
                                </div>
                            </a>
                        </div>
                        <h5>Results of Detection of Photo or Video: </h5>
                    </div>
                    <div className="mt-4 lighter-card p-2 rounded" style={{width: 'fit-content'}}>
                        <img src={`${BASE_URL}/file/download/${report.filename}`} alt="test-img" style={{width: '300px'}} />
                    </div>
                </div>
            </section>
        </div>
    );
}

export default RescueReport;
