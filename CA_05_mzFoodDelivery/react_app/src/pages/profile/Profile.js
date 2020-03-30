import * as React from "react";
import Header from "../general/Header";
import Footer from "../general/Footer";
import "../../Assets/styles/profile-style.css";


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
                    <div className="profile-info row no-gutters">
                        <div className="col-auto">
                            <div className="name">
                                <div className="profile-icon">
                                    <i className="flaticon-account"></i>
                                </div>
                                <div className="name-text">
                                    احسان خامس‌پناه
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
                                        ۰۹۱۲۳۴۵۶۷۸۹
                                    </div>
                                </div>
                                <div className="email  row justify-content-center align-items-center">
                                    <div className="col-auto">
                                        <i className="flaticon-mail"></i>
                                    </div>
                                    <div className="col-auto">
                                        ekhamespanah@yahoo.com
                                    </div>
                                </div>
                                <div className="credit  row justify-content-center align-items-center">
                                    <div className="col-auto">
                                        <i className="flaticon-card"></i>
                                    </div>
                                    <div className="col-auto">
                                        ۱۰۰۰۰ تومان
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


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

                                    <div className="tab1 charge-form">
                                        <form action="tmp">
                                            <div className="row justify-content-center align-items-center">
                                                <div className="col-8 my-2">
                                                    <div>
                                                        <input type="text" placeholder="میزان افزایش اعتبار" />
                                                    </div>
                                                </div>
                                                <div className="col-4 my-2">
                                                    <div>
                                                        <input type="submit" value="افزایش" />
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>

                                    <div className="orders tab2">
                                        <div className="order-item">
                                            <div className="row">
                                                <div className="col-2">
                                                    <div className="order-index">
                                    <span>
                                        ۱
                                    </span>
                                                    </div>
                                                </div>
                                                <div className="col-6">
                                                    <div className="order-restaurant">
                                                        <span>رستوران خامس</span>
                                                    </div>
                                                </div>
                                                <div className="col-4">
                                                    <div className="order-status">
                                                        <div className="status-box delivery">
                                        <span>
                                            پیک در مسیر
                                        </span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="order-item">
                                            <div className="row">
                                                <div className="col-2">
                                                    <div className="order-index">
                                    <span>
                                        ۲
                                    </span>
                                                    </div>
                                                </div>
                                                <div className="col-6">
                                                    <div className="order-restaurant">
                                                        <span>رستوران خامس</span>
                                                    </div>
                                                </div>
                                                <div className="col-4">
                                                    <div className="order-status">
                                                        <div className="status-box searching">
                                        <span>
                                            در جست‌و‌جوی پیک
                                        </span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="order-item">
                                            <div className="row">
                                                <div className="col-2">
                                                    <div className="order-index">
                                    <span>
                                        ۳
                                    </span>
                                                    </div>
                                                </div>
                                                <div className="col-6">
                                                    <div className="order-restaurant">
                                                        <span>رستوران خامس</span>
                                                    </div>
                                                </div>
                                                <div className="col-4">
                                                    <div className="order-status">
                                                        <div className="status-box delivered clickable">
                                        <span>
                                            مشاهده فاکتور
                                        </span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="order-item">
                                            <div className="row">
                                                <div className="col-2">
                                                    <div className="order-index">
                                    <span>
                                        ۴
                                    </span>
                                                    </div>
                                                </div>
                                                <div className="col-6">
                                                    <div className="order-restaurant">
                                                        <span>رستوران خامس</span>
                                                    </div>
                                                </div>
                                                <div className="col-4">
                                                    <div className="order-status">
                                                        <div className="status-box delivered clickable">
                                        <span>
                                            مشاهده فاکتور
                                        </span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="order-item">
                                            <div className="row">
                                                <div className="col-2">
                                                    <div className="order-index">
                                    <span>
                                        ۵
                                    </span>
                                                    </div>
                                                </div>
                                                <div className="col-6">
                                                    <div className="order-restaurant">
                                                        <span>رستوران خامس</span>
                                                    </div>
                                                </div>
                                                <div className="col-4">
                                                    <div className="order-status">
                                                        <div className="status-box delivered clickable">
                                        <span>
                                            مشاهده فاکتور
                                        </span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="order-item">
                                            <div className="row">
                                                <div className="col-2">
                                                    <div className="order-index">
                                    <span>
                                        ۶
                                    </span>
                                                    </div>
                                                </div>
                                                <div className="col-6">
                                                    <div className="order-restaurant">
                                                        <span>رستوران خامس</span>
                                                    </div>
                                                </div>
                                                <div className="col-4">
                                                    <div className="order-status">
                                                        <div className="status-box delivered clickable">
                                        <span>
                                            مشاهده فاکتور
                                        </span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="order-item">
                                            <div className="row">
                                                <div className="col-2">
                                                    <div className="order-index">
                                    <span>
                                        ۷
                                    </span>
                                                    </div>
                                                </div>
                                                <div className="col-6">
                                                    <div className="order-restaurant">
                                                        <span>رستوران خامس</span>
                                                    </div>
                                                </div>
                                                <div className="col-4">
                                                    <div className="order-status">
                                                        <div className="status-box delivered clickable">
                                        <span>
                                            مشاهده فاکتور
                                        </span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                        </div>
                    </div>

                </main>


                <Footer/>
            </div>
        );
    }

}