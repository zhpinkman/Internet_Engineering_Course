import React from "react";

export default class HomeTopSection extends React.Component {
    render() {
        return (
            <div className="app-background">
                <div className="behind-img">
                    <img src={require("../../Assets/images/Cover Photo.jpg")} alt=""/>
                </div>
                <div className="center-things">
                    <div className="loghme-icon">
                        <img className="home-loghme-icon-img" src={require("../../Assets/images/LOGO.png")} alt=""/>
                    </div>
                    <div className="loghme-title">
                        اولین و بزرگترین وب‌سایت سفارش آنلاین غذا در دانشگاه تهران
                    </div>
                </div>
            </div>
        );
    }
}