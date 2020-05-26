import * as React from "react";
import Header from "../general/Header";
import Footer from "../general/Footer";

import UserService from "../../services/UserService";
import "../../Assets/styles/profile-style.css";
import {creditRefresh} from "../../services/subjects/MessageService";
import {toast} from "react-toastify";
import {scrollToTop} from "../../utils/utils";
import Charge from "./Charge";
import Orders from "./Orders";
import ProfileHeader from "./ProfileHeader";


export default class Profile extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            user: null,
            isLoading: false
        };

    }


    componentDidMount() {
        document.title = "MzFood Account";
        scrollToTop();
        toast.configure({rtl: true, className: "text-center"});
        creditRefresh.asObservable().subscribe(() => {
            this.getUser();
        });
        this.getUser();
        console.log("started getting user");

    }



    async getUser() {
        UserService.getUser().then(user => {
            console.log(user.data);
            this.setState({user: user.data});
            document.title = this.state.user.firstName + " - MzFood";
        });
    }




    render() {
        return (
            <div className="h-100">
                <Header/>
                {this.state.user !== null ? this.renderPage() : this.renderLoading()}
                <Footer/>
            </div>
        );
    }

    renderLoading() {
        return (
            <div className="h-100 m-5 center-text p-5">
                <div className="spinner-grow text-danger m-5" role="status">
                    <span className="sr-only">Loading...</span>
                </div>
            </div>
        );
    }

    renderPage() {
        return (
            <main>

                <ProfileHeader user={this.state.user}/>

                <div className="orders-charge-wrapper">
                    <div className="orders-charge">

                        <input type="radio" id="tab-1" name="tab-group-1"/>
                        <input type="radio" id="tab-2" name="tab-group-1" defaultChecked/>

                        <div className="selector row">
                            <label htmlFor="tab-1" className="selector-item  col select-tab1">
                        <span>
                            سفارش‌ها
                        </span>
                            </label>

                            <label htmlFor="tab-2" className="selector-item col select-tab2">
                                            <span>
                            افزایش اعتبار
                        </span>
                            </label>
                        </div>

                        <Charge/>

                        <Orders/>

                    </div>
                </div>

            </main>
        );
    }

}