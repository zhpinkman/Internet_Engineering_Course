import * as React from "react";
import Header from "../general/Header";
import Footer from "../general/Footer";

import UserService from "../../services/UserService";
import "../../Assets/styles/profile-style.css";
import OrderItem from "./OrderItem";
import {creditRefresh} from "../../services/subjects/MessageService";
import {toast} from "react-toastify";
import {TOAST_MESSAGE_CREDIT_MORE_THAN_0, TOAST_MESSAGE_OK} from "../../config/config";
import {enToFaNumber, scrollToTop} from "../../utils/utils";


export default class Profile extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            user: null,
            amount: 0,
            orders: {},
            isLoading: false
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

    componentDidMount() {
        document.title = "MzFood Account";
        scrollToTop();
        toast.configure({rtl: true, className: "text-center"});
        creditRefresh.asObservable().subscribe(() => {
            this.getUser();
            this.getOrders();
        });
        this.getUser();
        this.getOrders();
        console.log("started getting user");
    }

    async getUser() {
        UserService.getUser().then(user => {
            console.log(user.data);
            this.setState({user: user.data});
            document.title = this.state.user.fullName + " - MzFood";
        });
    }

    async getOrders() {
        UserService.getOrders().then(orders => {
            this.setState({orders: orders.data});
            console.log("orders");
            console.log(orders.data);
        })
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
                <div className="profile-info row no-gutters">
                    <div className="col-auto">
                        <div className="name">
                            <div className="profile-icon">
                                <i className="flaticon-account"/>
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
                                    <i className="flaticon-phone"/>
                                </div>
                                <div className="col-auto">
                                    {enToFaNumber(this.state.user.phoneNumber)}
                                </div>
                            </div>
                            <div className="email  row justify-content-center align-items-center">
                                <div className="col-auto">
                                    <i className="flaticon-mail"/>
                                </div>
                                <div className="col-auto">
                                    {this.state.user.email}
                                </div>
                            </div>
                            <div className="credit  row justify-content-center align-items-center">
                                <div className="col-auto">
                                    <i className="flaticon-card"/>
                                </div>
                                <div className="col-auto">
                                    {enToFaNumber(this.state.user.credit)} تومان
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                {/*<Link to={"/p"} >LINK</Link>*/}
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

                        <div className="tab1 charge-form">
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
                        </div>
                        <div className="orders tab2">
                            {
                                this.state.orders && this.state.orders.length > 0
                                    ? (<div> {this.state.orders.map((order, index) => {
                                        return <OrderItem order={order} index={index + 1} key={"ORDER" + index}/>
                                    })}
                                    </div>) : (
                                        <div className="d-flex justify-content-center align-items-center w-100 m-2">
                                            <span>خالی</span>
                                        </div>
                                    )}
                        </div>


                    </div>
                </div>

            </main>
        );
    }

}