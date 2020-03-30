import React from 'react';
import "../../Assets/styles/home-styles.css";
import Header from "../general/Header";

export default class Home extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            test: 1
        };
    }

    async componentDidMount() {

    }

    render() {
        return (
            <div>
                <Header/>
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

                <div className="search-bar">
                    <div className="outer-box">
                        <form
                            action=""
                            className="w-100 m-0 d-flex justify-content-center align-items-center">
                            <div className="row w-100">
                                <div className="food-name col">
                                    <input
                                        className="search-item"
                                        placeholder="نام غذا"/>
                                </div>
                                <div className="restaurant-name col">
                                    <input className="search-item" placeholder="نام رستوران"/>
                                </div>
                                <div className="search-btn col">
                                    <input className="search-item" type="submit" value="جست‌و‌جو"/>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

                <main>
                    <div className="food-party">
                        food party
                        <div className="carousel"
                             data-flickity='{ "freeScroll": true, "contain": true, "prevNextButtons": false, "pageDots": false }'>
                            <div className="carousel-cell"></div>
                            <div className="carousel-cell"></div>
                            <div className="carousel-cell"></div>
                            <div className="carousel-cell"></div>
                            <div className="carousel-cell"></div>
                        </div>

                    </div>
                    <div className="restaurants-list">
                        restaurants list
                    </div>
                </main>


                <footer>
                    footer
                </footer>
                {/*<script src='https://npmcdn.com/flickity@2/dist/flickity.pkgd.js'></script>*/}
            </div>
        );
    }

}