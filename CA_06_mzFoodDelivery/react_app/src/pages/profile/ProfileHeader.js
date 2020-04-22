import * as React from "react";
import "../../Assets/styles/profile-style.css";
import {enToFaNumber} from "../../utils/utils";


export default class ProfileHeader extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
        }
    }

    componentDidMount() {
    }


    render() {
        return (
            <div className="profile-info row no-gutters">
                <div className="col-auto">
                    <div className="name">
                        <div className="profile-icon">
                            <i className="flaticon-account"/>
                        </div>
                        <div className="name-text">
                            {this.props.user.firstName + this.props.user.lastName}
                        </div>
                    </div>
                </div>
                <div className="mr-auto col-auto h-auto">
                    <div className="other-info h-100">
                        <div className="phone row justify-content-center align-items-center">
                            <div className="col-auto">
                                <i className="flaticon-phone"/>
                            </div>
                            <div className="col-auto">
                                {enToFaNumber(this.props.user.phoneNumber)}
                            </div>
                        </div>
                        <div className="email  row justify-content-center align-items-center">
                            <div className="col-auto">
                                <i className="flaticon-mail"/>
                            </div>
                            <div className="col-auto">
                                {this.props.user.email}
                            </div>
                        </div>
                        <div className="credit  row justify-content-center align-items-center">
                            <div className="col-auto">
                                <i className="flaticon-card"/>
                            </div>
                            <div className="col-auto">
                                {enToFaNumber(this.props.user.credit)} تومان
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    );
    }

}