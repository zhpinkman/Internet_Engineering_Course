import React from "react";
import Flickity from 'flickity';
import 'flickity/dist/flickity.min.css';

import TextTitleCenter from "../../general/text/TextTitleCenter";
import RemainingTime from "./RemainingTime";
import "./foodParty.css";
import FoodPartyCard from "./FoodPartyCard";
import FoodPartyService from "../../../services/FoodPartyService";
import Slider from "./Slider";


export default class FoodParty extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            partyFoods: null
        };
    }

    componentDidMount() {
        this.getPartyFoods();
    }

    componentWillUnmount() {

    }

    async getPartyFoods() {
        let partyFoods = await FoodPartyService.getPartyFoods();
        console.log(partyFoods);
        this.setState({
            partyFoods: partyFoods
        });

    }

    render() {
        return (
            <div className="food-party">
                <TextTitleCenter text="جشن غذا!"/>
                <RemainingTime remainingTime="21:48"/>

                <div className="carousel">
                    <Slider
                        options={{
                            autoPlay: 4000,
                            pauseAutoPlayOnHover: true,
                            // wrapAround: true,
                            // fullscreen: true,
                            // adaptiveHeight: true,

                            freeScroll: true,
                            contain: true,
                            prevNextButtons: false,
                            pageDots: false
                        }}
                    >
                        {this.foodPartyList()}
                    </Slider>
                </div>

            </div>

        );
    }

    foodPartyList() {
        console.log(this.state.partyFoods);
        if (this.state.partyFoods == null) {
            return (
                <div className="spinner-grow text-danger" role="status">
                    <span className="sr-only">Loading...</span>
                </div>
            );
        } else {
            return this.state.partyFoods.map((partyFood) =>
                <FoodPartyCard partyFood={partyFood}/>
            );
        }
    }
}

