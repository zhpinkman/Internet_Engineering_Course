import * as React from "react";
import "../../Assets/styles/profile-style.css";

import OrderItem from "./OrderItem";
import UserService from "../../services/UserService";
import {creditRefresh} from "../../services/subjects/MessageService";


export default class Orders extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            orders : null
        }
    }

    componentDidMount() {
        this.getOrders();
        creditRefresh.asObservable().subscribe(() => {
            this.getOrders();
        });
        this.intervalTimer = setInterval(() => {
            this.timerFunc();
        }, 5000);
    }

    componentWillUnmount() {
        clearInterval(this.intervalTimer);
    }

    timerFunc() {
        this.getOrders();
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
        );
    }

}