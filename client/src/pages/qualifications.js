import { useEffect, useState } from 'react'
import ClickList from '../components/ClickList'
import { getQualifications, createQualification } from '../services/dataService'
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

const CreateQualificationForm = ({ onQualificationCreated }) => {
    const [description, setDescription] = useState('')
    const [error, setError] = useState('')

    const handleSubmit = async (event) => {
        event.preventDefault()

        const trimmedDescription = description.trim()

        if (trimmedDescription.length === 0) {
            setError('description cannot be empty')
            return
        }

        try {
            await createQualification(trimmedDescription)

            setDescription('')
            setError('')
            onQualificationCreated()
        } catch (error) {
            setError('unable to create qualification')
        }
    }

    return (
        <div style={grayContainerStyle}>
            <h2>Create New Qualification</h2>

            {error !== '' ? <div>{error}</div> : null}

            <form onSubmit={handleSubmit}>
                <div>
                    <label>
                        Description:
                        <input
                            type="text"
                            value={description}
                            onChange={(event) => setDescription(event.target.value)}
                        />
                    </label>
                </div>

                <button type="submit">
                    Submit
                </button>
            </form>
        </div>
    )
}

const Qualifications = () => {
    const [qualifications, setQualifications] = useState([])
    const [showCreateQualificationForm, setShowCreateQualificationForm] = useState(false)

    const loadQualifications = () => {
        getQualifications().then(setQualifications)
    }

    useEffect(() => {
        loadQualifications()
    }, [])

    const active = LocationID('qualifications', qualifications, 'description')

    return (
        <div style={pageStyle}>
            <h1>
                Company Qualifications
            </h1>

            <button onClick={() => setShowCreateQualificationForm(!showCreateQualificationForm)}>
                {showCreateQualificationForm ? 'Cancel' : 'Create New Qualification'}
            </button>

            {showCreateQualificationForm ? (
                <CreateQualificationForm
                    onQualificationCreated={() => {
                        loadQualifications()
                        setShowCreateQualificationForm(false)
                    }}
                />
            ) : null}

            <ClickList active={active} list={qualifications} item={Qualification} path='/qualifications' id='description' />
        </div>
    )
}

export default Qualifications