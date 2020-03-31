import React from "react";
import {enToFaNumber} from "../../../../utils/utils";
import "../foodParty.css"

export default class StockTag extends React.Component {
    render() {
        if (this.props.stock == 0) {
            return (
                <div>
                    ناموجود
                </div>
            );
        } else {
            return (
                <div className="center-text stock-tag">
                    موجودی:
                    {enToFaNumber(this.props.stock)}
                </div>
            );
        }
    }
}