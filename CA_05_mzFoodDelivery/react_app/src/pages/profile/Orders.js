import * as React from "react";
import "../../Assets/styles/profile-style.css";

import OrderItem from "./OrderItem";


export default class Orders extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            orders : []
        }

        for (var i = 0; i < 8; i++) {
            this.state.orders.push( <OrderItem index={i} /> );
        }
    }


    render() {
        return (
            <div className="orders tab2">
                {this.state.orders}
            </div>
        );
    }

}