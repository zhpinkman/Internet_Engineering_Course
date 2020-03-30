import * as React from "react";
import "../../Assets/styles/profile-style.css";
import UserService from "../../services/UserService";


export default class ProfileHeader extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            user: {}
        }
    }

    componentDidMount() {
        console.log("started getting user");
        UserService.getUser().then(user => {
            console.log(user.data)
            this.setState({user: user.data})
        })
    }


    render() {
        return (
        <div className="profile-info row no-gutters">
            <div className="col-auto">
                <div className="name">
                    <div className="profile-icon">
                        <i className="flaticon-account"></i>
                    </div>
                    <div className="name-text">
                        {this.state.user.fullName}
                    </div>
                </div>
            </div>
            <div className="mr-auto col-auto h-auto">
                <div className="other-info h-100">
                    <div className="phone row justify-content-center align-items-center">
                        <div className="col-auto">
                            <i className="flaticon-phone"></i>
                        </div>
                        <div className="col-auto">
                            {this.state.user.phoneNumber}
                        </div>
                    </div>
                    <div className="email  row justify-content-center align-items-center">
                        <div className="col-auto">
                            <i className="flaticon-mail"></i>
                        </div>
                        <div className="col-auto">
                            {this.state.user.email}
                        </div>
                    </div>
                    <div className="credit  row justify-content-center align-items-center">
                        <div className="col-auto">
                            <i className="flaticon-card"></i>
                        </div>
                        <div className="col-auto">
                            {this.state.user.credit}  تومان
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
    }

}