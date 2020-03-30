import * as React from "react";
import Header from "../general/Header";
import Footer from "../general/Footer";

import ProfileHeader from "./ProfileHeader";
import Orders from "./Orders";
import Charge from "./Charge";


export default class Profile extends React.Component{

    constructor(props) {
        super(props);

        this.state = {
            user: {}
        }
    }



    render() {
        return (
            <div>
                <Header/>

                <main>


                    <ProfileHeader/>


                    <div className="orders-charge-wrapper">
                        <div className="orders-charge">

                            <input type="radio" id="tab-1" name="tab-group-1" checked />
                                <input type="radio" id="tab-2" name="tab-group-1" />

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


                <Footer/>
            </div>
        );
    }

}