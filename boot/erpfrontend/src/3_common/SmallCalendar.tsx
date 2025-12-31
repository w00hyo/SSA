import{useState} from "react";
import { CalendarRow, CalTopMargin } from "../stylesjs/Content.styles";
import {
  CalendarRow2,
  CalendarWrapper2,
  SideButton,
  Month,
  Days,
  Day,
  Dates,
  DateCell,
} from "../stylesjs/Content.styles";
const SmallCalendar = () => {
const [open,setOpen] = useState(false); 

const days = ["일","월","화","수","목","금","토"];
const dates = Array.from({length:30},(_, i) => i +1 );
    
    return(
        <>
        <CalTopMargin>
<CalendarRow2>
<CalendarWrapper2 $open={open}>
<Month></Month>
<Days>
{days.map((d) => (<Day key={d}>{d}</Day>))}
</Days>
<Date>
{dates.map((date) => (<DateCell key={date}>{date}</DateCell>
))}
</Date>
</CalendarWrapper2>

<SideButton onClick={() => setOpen((prev) => !prev)}>
    {open ? "◀" : "▶"}
</SideButton>
</CalendarRow2>
</CalTopMargin>
        </>
    )
}
export default SmallCalendar