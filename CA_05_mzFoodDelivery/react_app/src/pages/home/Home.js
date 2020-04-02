import React from 'react';
import "../../Assets/styles/home-styles.css";
import Header from "../general/Header";
import Footer from "../general/Footer";
import HomeTopSection from "./HomeTopSection";
import HomeSearchBar from "./HomeSearchBar";
import FoodParty from "./foodParty/FoodParty";
import Restaurants from "./restaurants/Restaurants";

export default class Home extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            test: 1
        };
    }

    async componentDidMount() {
        document.title = "MzFood Delivery";
    }

    render() {
        return (
            <div>
                <Header/>
                <HomeTopSection/>
                <HomeSearchBar/>

                <FoodParty/>
                <Restaurants/>

                <br/>
                <Footer/>
            </div>
        );
    }

}