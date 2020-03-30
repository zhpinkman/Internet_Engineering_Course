import React from 'react';
import "../../Assets/styles/home-styles.css";
import Header from "../general/Header";
import Footer from "../general/Footer";
import HomeTopSection from "./HomeTopSection";
import HomeSearchBar from "./HomeSearchBar";
import FoodParty from "./foodParty/FoodParty";

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
                <HomeTopSection/>
                <HomeSearchBar/>

                <FoodParty/>

                <div className="restaurants-list">
                    restaurants list
                    <div className="carousel"
                         data-flickity='{ "freeScroll": true, "contain": true, "prevNextButtons": false, "pageDots": false }'>
                        <div className="carousel-cell"></div>
                        <div className="carousel-cell"></div>
                        <div className="carousel-cell"></div>
                        <div className="carousel-cell"></div>
                        <div className="carousel-cell"></div>
                    </div>
                </div>
                <br/>


                <Footer/>
                {/*<script src='https://npmcdn.com/flickity@2/dist/flickity.pkgd.js'></script>*/}
            </div>
        );
    }

}