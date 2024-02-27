import { useState } from 'react';
import { Card, Form } from 'react-bootstrap';
import { isBlank } from '../util/Common';
import { Link, useNavigate } from 'react-router-dom';
import { signUp } from '../axios/authClient';

function SignUp() {
    const [inputData, setInputData] = useState({});
    const [authority, setAuthority] = useState(false);
    const [rescue, setRescue] = useState(false);
    const navigate = useNavigate();

    function handleInputChange(e) {
        setInputData({...inputData, [e.target.name]: e.target.value});
    }

    async function handleSignUp() {
        const { username, email, password, cPassword } = inputData;
        if (isBlank(username)) {
            alert("Username is required.");
            return;
        }
        if (isBlank(email)) {
            alert("Email is required.");
            return;
        }
        if (isBlank(password)) {
            alert("Password is required.");
            return;
        }
        if (isBlank(cPassword)) {
            alert("Confirm Password is required.");
            return;
        }
        if (password !== cPassword) {
            alert("Passwords do not match.")
            return;
        }
        if (!authority && !rescue) {
            alert("Atleast one of Authority and Rescue must be selected.");
            return;
        }
        const roles = [];
        if (authority) {
            roles.push('authority');
        }
        if (rescue) {
            roles.push('rescue');
        }
        const payload = { username, email, password, roles };
        try {
            await signUp(payload);
            alert("Sign Up Successful.");
            navigate("/login");
        } catch (e) {
            alert("Sign Up Failed, try differend credentials!");
        }
    }

    return (
        <div className="d-flex justify-content-center">
            <Card className='mt-5' style={{marginTop: '50px', width: '400px'}}>
                <Card.Body className='ms-4 me-4'>
                    <h1 className='tx-blue mt-4 ms-2'>
                        Sign Up
                    </h1>
                    <Form.Group className='mt-4'>
                        <Form.Control className='rounded-pill' placeholder='Username' name='username'
                        value={inputData.username} onChange={handleInputChange} />
                    </Form.Group>
                    <Form.Group className='mt-4'>
                        <Form.Control className='rounded-pill' placeholder='Email' name='email' type='email'
                        value={inputData.email} onChange={handleInputChange} />
                    </Form.Group>
                    <Form.Group className='mt-4'>
                        <Form.Control className='rounded-pill' placeholder='Password' name='password' type='password'
                        value={inputData.password} onChange={handleInputChange} />
                    </Form.Group>
                    <Form.Group className='mt-4'>
                        <Form.Control className='rounded-pill' placeholder='Confirm Password' name='cPassword' type='password'
                        value={inputData.cPassword} onChange={handleInputChange} />
                    </Form.Group>

                    <div className="mt-3">
                        <div className="d-flex">
                            <input className='me-1' type="checkbox" name="authority" id="authority"
                            value={authority} onChange={() => setAuthority(state => !state)} />
                            <span className="tx-light-blue">As Authority Manager</span>
                        </div>
                        <div className="d-flex">
                            <input className='me-1' type="checkbox" name="rescue" id="rescue"
                            value={rescue} onChange={() => setRescue(state => !state)} />
                            <span className="tx-light-blue">As Rescue Team</span>
                        </div>
                    </div>

                    <button className="bg-blue text-light w-100 mt-4 p-1 rounded-pill" onClick={handleSignUp}>Sign Up</button>

                    <Link to={'/login'} style={{textDecoration: 'none', color: 'white'}}>
                        <div style={{textAlign: 'center'}} className='mt-2 mb-3 tx-purple'>Already Have Account? Sign In</div>
                    </Link>
                </Card.Body>
            </Card>
        </div>
    );
}

export default SignUp;