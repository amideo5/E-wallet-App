import React from 'react'
import { Navbar,Nav } from 'react-bootstrap';
import Container from 'react-bootstrap/Container'

const NavBar = () => {

    const handleLogout = () => {
        localStorage.removeItem('token')
    }

    return (
        <Navbar className="m-0 py-3" collapseOnSelect expand="lg" bg="primary" variant="dark">
            <Container fluid>
                <Navbar.Brand href="/">NEX E-Wallet</Navbar.Brand>
                <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                <Navbar.Collapse id="responsive-navbar-nav">
                    <Nav className="me-auto">
                    </Nav>
                    <Nav>
                        {/* <Nav.Link href="/">Sign In</Nav.Link>
                        <Nav.Link eventKey={2} href="/signup">
                            Sign Up
                        </Nav.Link> */}
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}

export default NavBar