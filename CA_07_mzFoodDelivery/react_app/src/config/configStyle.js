import { bounce } from 'react-animations';
import { StyleSheet} from 'aphrodite';

export let PRIMARY_COLOR = "#1A535C";
export let PRIMARY_VARIANT_COLOR = "#4ECDC4";
export let BACKGROUND_COLOR = "#F7FFF7";
export let SECONDARY_COLOR = "#FF6B6B";
export let SECONDARY_VARIANT_COLOR = "#FFE66D";

export const animationStyles = StyleSheet.create({
    bounce: {
        animationName: bounce,
        animationDuration: '5s'
    }
})