import { useState } from 'react';
import { Card, Form, Button } from 'react-bootstrap';
import { login } from '../axios/authClient';
import { isBlank } from '../util/Common';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../util/functions';

function Login() {
    const [credentials, setCredentials] = useState({});
    const [remember, setRemember] = useState(false);
    const [authority, setAuthority] = useState(false);
    const [rescue, setRescue] = useState(false);
    const navigate = useNavigate();
    const [isAuthenticated, setIsAuthenticated] = useAuth();

    function handleInputChange(e) {
        setCredentials({...credentials, [e.target.name]: e.target.value});
    }

    async function handleLogin() {
        console.log('handleLogin');
        console.log('credentials: ' + JSON.stringify(credentials));
        console.log('remember: ' + remember);
        console.log('authority: ' + authority);
        console.log('rescue: ' + rescue);

        const { username, password } = credentials;

        try {
            if (isBlank(username) || isBlank(password)) {
                alert('Username and Password are required.');
                return;
            }
            if (authority === rescue) {
                alert('Please select one of either authority or rescue.');
                return;
            }
            const role = authority ? 'authority' : 'rescue';
            const payload = { username, password, remember, role };
            console.log('payload: ' + JSON.stringify(payload));
            const { jwtToken } = (await login(payload)).data;
            console.log('jwtToken: ' + jwtToken);
            localStorage.setItem('access', jwtToken);
            localStorage.setItem('role', role);
            navigate('/');
            // setIsAuthenticated({authenticated: true, role: role});
            window.location.reload();
        } catch (e) {
            alert('Login failed: ' + e);
        }
    }


    return (
        <div className="d-flex justify-content-center">
            <Card className='mt-5' style={{textAlign: 'center', width: '400px'}}>
                <Card.Body className='ms-4 me-4'>
                    <h1 className='tx-blue mt-4'>
                        Welcome Back
                    </h1>
                    <Form.Group className='mt-5'>
                        <Form.Control className='rounded-pill' placeholder='Username' name='username'
                        value={credentials.username} onChange={handleInputChange} />
                    </Form.Group>
                    <Form.Group className='mt-4'>
                        <Form.Control className='rounded-pill' placeholder='Password' name='password' type='password'
                        value={credentials.password} onChange={handleInputChange} />
                    </Form.Group>
                    <div className="d-flex mt-2 justify-content-between">
                        <div className='d-flex me-4'>
                            <input className='me-1' type="checkbox" name="remember"
                            value={remember} onChange={() => setRemember((state) => !state)} />
                            <span style={{marginBottom: '0'}} className='tx-purple'>Remember Password</span>
                        </div>
                        <Link to={'/forgot-password'} style={{textDecoration: 'none', color: 'white'}}>
                            <span className="tx-purple">Forgot Password</span>
                        </Link>
                    </div>
                    <div className="mt-3">
                        <div className="d-flex">
                            <input className='me-1' type="checkbox" name="authority" id="authority" 
                            value={authority} onChange={() => setAuthority((state) => !state)} />
                            <span className="tx-light-blue">As Authority Manager</span>
                        </div>
                        <div className="d-flex">
                            <input className='me-1' type="checkbox" name="rescue" id="rescue"
                            value={rescue} onChange={() => setRescue((state) => !state)} />
                            <span className="tx-light-blue">As Rescue Team</span>
                        </div>
                    </div>

                    <button className="bg-blue text-light w-100 mt-4 p-1 rounded-pill" onClick={handleLogin}>Login</button>

                    <Link to={'/signup'} style={{textDecoration: 'none', color: 'white'}}>
                        <div className='mt-2 tx-purple mb-3'>Don't have an account? Sign Up</div>
                    </Link>
                </Card.Body>
            </Card>
        </div>
    );
}

export default Login;