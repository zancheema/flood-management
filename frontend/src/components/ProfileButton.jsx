import { Dropdown } from "react-bootstrap";
import { CgProfile } from "react-icons/cg";

function ProfileButton({ title }) {
    function logout() {
        localStorage.removeItem('access');
        window.location.reload();
    }

    return (
        <Dropdown className='mt-3'>
            <Dropdown.Toggle classname="ProfileButton-toggle" id="dropdown-basic"
            style={{backgroundColor: 'var(--mid-bg)', border: 'none', marginTop: '100px'}}>

                <div style={{height: 'fit-content', color: 'black'}} className="bg-light pt-1 pb-1 ps-4 pe-4 rounded-pill vertical-center me-3">
                    <CgProfile className="me-3 vertical-center" style={{height: '20px', width: 'auto'}} />
                    <span>{title}</span>
                </div>
            </Dropdown.Toggle>

            <Dropdown.Menu>
                <Dropdown.Item onClick={logout}>Logout</Dropdown.Item>
            </Dropdown.Menu>
        </Dropdown>
    );
}

export default ProfileButton;
