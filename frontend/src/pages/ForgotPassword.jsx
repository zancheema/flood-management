import { useState } from 'react';
import { Card, Form } from 'react-bootstrap';
import { isBlank } from '../util/Common';
import { useNavigate } from 'react-router-dom';
import { resetPassword, signUp } from '../axios/authClient';

function ForgotPassword() {
    const [inputData, setInputData] = useState({});
    const navigate = useNavigate();

    function handleInputChange(e) {
        setInputData({...inputData, [e.target.name]: e.target.value});
    }

    async function handleSubmit() {
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
        const payload = { username, email, password };
        try {
            await resetPassword(payload);
            alert('Password Reset Successful!')
            navigate('/login', { replace: true });
        } catch (e) {
            alert("Failed to reset password. Please check all fields.");
        }
    }

    return (
        <div className="d-flex justify-content-center">
            <Card className='mt-5' style={{marginTop: '50px', width: '400px'}}>
                <Card.Body className='ms-4 me-4'>
                    <h1 className='tx-blue mt-4 ms-2'>
                        Forgot Password
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

                    <button className="bg-blue text-light w-100 mt-4 mb-4 p-1 rounded-pill" onClick={handleSubmit}>Submit</button>
                </Card.Body>
            </Card>
        </div>
    );
}

export default ForgotPassword;