export default function enToFaNumber(text) {
    const arabicNumbers = ['۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹'];
    return String(text).split('').map(c => parseInt(c) ? arabicNumbers[parseInt(c)] : c=='0' ? '۰' : c).join('');
}