import { useEffect, useState } from 'react'
import ClickList from '../components/ClickList'
import { getQualifications } from '../services/dataService'
import LocationID from '../utils/location'
import { darkGrayContainerStyle, grayContainerStyle, pageStyle } from '../utils/styles'

const Qualification = (qualification, active) => {
    return (
        <div>
            <div>{qualification.description}</div>
            {active === true ? QualificationBody(qualification) : null}
        </div>
    )
}

const QualificationBody = (qualification) => {
    return (
        <div style={grayContainerStyle}>
            Workers: <ClickList list={qualification.workers} styles={darkGrayContainerStyle} path="/workers" />
        </div>
    )
}

const Qualifications = () => {
    const [qualifications, setQualifications] = useState([])
    useEffect(() => { getQualifications().then(setQualifications) }, [])
    const active = LocationID('qualifications', qualifications, 'description')
    return (
        <div style={pageStyle}>
            <h1>
                This page displays a table containing all the qualifications.
            </h1>
            <ClickList active={active} list={qualifications} item={Qualification} path='/qualifications' id='description' />
        </div>
    )
}

export default Qualifications