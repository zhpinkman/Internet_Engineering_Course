import * as React from "react";

import "../../Assets/styles/profile-style.css";
import UserService from "../../services/UserService";
import {toast} from "react-toastify";
import {TOAST_MESSAGE_CREDIT_MORE_THAN_0, TOAST_MESSAGE_OK} from "../../config/config";
import {creditRefresh} from "../../services/subjects/MessageService";


export  default  class Charge extends React.Component{


    constructor(props) {
        super(props);
        this.state = {
            amount: 0
        };


        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }


    handleChange(event) {
        event.preventDefault();
        this.setState({amount: event.target.value});
        event.preventDefault();
    }

    handleSubmit(event) {
        event.preventDefault();
        if (this.state.amount > 0) {
            this.setState({isLoading: true});
            UserService.charge(this.state.amount).then(resp => {
                creditRefresh.next();
                toast.success(TOAST_MESSAGE_OK);
                console.log(resp);
                this.setState({
                    user: resp.data,
                    isLoading: false
                });
            });
        } else {
            toast.warn(TOAST_MESSAGE_CREDIT_MORE_THAN_0);
        }
    }


    render() {
        return (<div className="tab1 charge-form">
            <form onSubmit={this.handleSubmit}>
                <div className="row justify-content-center align-items-center">
                    <div className="col-8 my-2">
                        <div>
                            <input type="text" value={this.state.amount}
                                   onChange={this.handleChange} name="amount"
                                   placeholder="میزان افزایش اعتبار"/>
                        </div>
                    </div>
                    <div className="col-4 my-2">
                        <div>
                            <button type="submit" value="افزایش">
                                افزایش
                                {this.state.isLoading &&
                                <span className="spinner-border mr-2" role="status" aria-hidden="true"/>
                                }
                            </button>
                        </div>
                    </div>
                </div>
            </form>
        </div>);
    }

}