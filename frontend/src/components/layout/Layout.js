import React, { Component } from 'react';
import SignUp from '../register-user/RegisterUser';
import './Layout.scss';

class Layout extends Component {
    render() {
        return (
            <div className="main-body">
                <div className="main">
                    <SignUp />
                </div>


                {/* Footer */}
                <div className="main-footer">
                    <footer>
                        &copy; <a href="" /> HackPro
                   </footer>

                </div>
            </div>
        );
    }
}

export default Layout;

