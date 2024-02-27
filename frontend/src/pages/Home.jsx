import logo from "../assets/logo.webp";
import droneImg from "../assets/drone-picture-home.webp";
import { AiOutlineArrowRight } from "react-icons/ai";
import { BsFillArrowRightCircleFill } from "react-icons/bs";
import "./Home.css";
import { Link } from "react-router-dom";

function Home() {

    return (
        <>
            <header className="d-flex justify-content-between">
                <img className="logo" src={logo} alt="Logo" />
                <Link to="/signup" style={{textDecoration: 'none'}} className="mt-4">
                    <div style={{height: 'fit-content'}} className="bg-light pt-1 pb-1 rounded-pill vertical-center me-3 button-link">
                        <span className="ps-5 pe-1">Sign Up</span>
                        <AiOutlineArrowRight className="pe-2"  style={{height: '20px', width: 'auto'}} />
                    </div>
                </Link>
            </header>
            <section className="m-4 rounded card-interior text-light d-flex justify-content-between p-3">
                <div id="message">
                    <div id="hashtag-pill" className="rounded-pill p-2">
                        #floodmanagement
                    </div>
                    <h4 className="mt-4">
                        AI-Driven Drone Solution for Flood
                        Rescue and Disaster Management
                        in UAE
                    </h4>
                    <p>
                        Welcome to our website dedicated to r flood management through
                        the power of Artificial Intelligence (AI) and advanced Drone
                        technology. Our mission is to provide innovative solutions for
                        early flood detection, rapid response, and effective recovery
                        efforts.
                    </p>
                    <Link to="/login" style={{textDecoration: 'none'}}>
                        <div className="mt-4 rounded-pill pt-1 pb-1 ps-3 button-link">
                            <div className="d-flex">
                                <span  className="ps-5 pe-5">Login</span>
                                <BsFillArrowRightCircleFill style={{height: '30px', width: 'auto'}} className="pe-1" />
                            </div>
                        </div>
                    </Link>
                </div>
                <img src={droneImg} alt="drone-img" className="rounded" />
            </section>
        </>
    );
}

export default Home;
