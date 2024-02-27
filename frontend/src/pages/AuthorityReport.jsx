import logo from "../assets/logo.webp";
import { CgProfile } from "react-icons/cg";
import "./Authority.css";
import "./AuthoritySendNotification.css";
import testImg from "../assets/test.jpg";
import "./AuthorityReport.css";
import { Link, useNavigate, useParams } from "react-router-dom";
import { useState, useEffect } from "react";
import { BASE_URL } from "../util/Common";
import { getAuthorityReport } from "../axios/reportClient";
import ProfileButton from "../components/ProfileButton";

function AuthorityReport() {
    const { reportId } = useParams();
    const navigate = useNavigate();

    const [report, setReport] = useState({});

    useEffect(() => {
        console.log('reportId: ' + reportId);
        getReport();
    }, []);

    async function getReport() {
        const report = (await getAuthorityReport(reportId)).data;
        setReport(report);
    }

    function sendNotification(reportId) {
        navigate(`/authority/notification/send/${reportId}`);
    }

    return (
        <div className="RescueTeam">
        
            <header className="d-flex justify-content-between">
                <img className="logo" src={logo} alt="Logo" />
                <nav className="vertical-center d-flex">
                    <h4 style={{marginBottom: '0'}} className="me-3"><strong>Report</strong></h4>
                    <h5 style={{marginBottom: '0'}} className="me-3">Notification</h5>
                    <Link to={'/authority/location'} style={{textDecoration: 'none', color: 'white'}}>
                        <h5 style={{marginBottom: '0'}}>Location</h5>
                    </Link>
                </nav>
                <ProfileButton title={'Authority manager'} />
            </header>
            <section className="m-4 rounded card-interior text-light p-4 scrollable d-flex justify-content-end" style={{maxHeight: '470px'}}>
                <div style={{width: '50%'}} className="me-5">
                    <div className="lighter-card p-2 rounded" >
                        <h5>Report ID: {report.reportId}</h5>
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
                <div className="d-flex flex-column-reverse">
                    <button 
                        className="rounded-pill bg-light ps-5 pe-5 pt-1 pb-1" 
                        id="send-notification-button"
                        onClick={() => sendNotification(report.reportId)}>
                        Send Notification
                    </button>
                </div>
            </section>
        </div>
    );
}

export default AuthorityReport;
