import { useEffect, useState } from 'react'
import ClickList from '../components/ClickList'
import { getWorkers, getQualifications, createWorker } from '../services/dataService'
import LocationID from '../utils/location'
import { darkGrayContainerStyle, grayContainerStyle, pageStyle } from '../utils/styles'

const Worker = (worker, active) => {
   return (
       <div>
           <div>{worker.name}</div>
           {active === true ? WorkerBody(worker) : null}
       </div>
   )
}

const WorkerBody = (worker) => {
    return (
        <div style={grayContainerStyle}>
            <div>
                Name: {worker.name}
            </div>

            <div>
                Salary: {worker.salary}
            </div>

            <div>
                Current Workload: {worker.workload}
            </div>

            <div>
                Qualifications: <ClickList list={worker.qualifications} styles={darkGrayContainerStyle} path="/qualifications" />
            </div>

            <div>
                Projects: <ClickList list={worker.projects} styles={darkGrayContainerStyle} path="/projects" />
            </div>
        </div>
    )
}

const CreateWorkerForm = ({ qualifications, onWorkerCreated }) => {
    const [name, setName] = useState('')
    const [salary, setSalary] = useState('')
    const [selectedQualifications, setSelectedQualifications] = useState([])
    const [error, setError] = useState('')

    const toggleQualification = (description) => {
        if (selectedQualifications.includes(description)) {
            setSelectedQualifications(selectedQualifications.filter((q) => q !== description))
        } else {
            setSelectedQualifications([...selectedQualifications, description])
        }
    }

    const handleSubmit = async (event) => {
        event.preventDefault()

        const trimmedName = name.trim()
        const parsedSalary = Number(salary)

        if (trimmedName.length === 0) {
            setError('name cannot be empty')
            return
        }

        if (salary === '' || Number.isNaN(parsedSalary) || parsedSalary < 0) {
            setError('salary must be a non-negative number')
            return
        }

        if (selectedQualifications.length === 0) {
            setError('worker must have at least one qualification')
            return
        }

        try {
            await createWorker(trimmedName, selectedQualifications, parsedSalary)

            setName('')
            setSalary('')
            setSelectedQualifications([])
            setError('')

            onWorkerCreated()
        } catch (error) {
            setError('unable to create worker')
        }
    }

    return (
        <div style={grayContainerStyle}>
            <h2>Create New Worker</h2>

            {error !== '' ? <div>{error}</div> : null}

            <form onSubmit={handleSubmit}>
                <div>
                    <label>
                        Name:
                        <input
                            type="text"
                            value={name}
                            onChange={(event) => setName(event.target.value)}
                        />
                    </label>
                </div>

                <div>
                    <label>
                        Salary:
                        <input
                            type="number"
                            value={salary}
                            onChange={(event) => setSalary(event.target.value)}
                        />
                    </label>
                </div>

                <div>
                    Qualifications:
                    {qualifications.map((qualification) => (
                        <label key={qualification.description} style={{ display: 'block' }}>
                            <input
                                type="checkbox"
                                checked={selectedQualifications.includes(qualification.description)}
                                onChange={() => toggleQualification(qualification.description)}
                            />
                            {qualification.description}
                        </label>
                    ))}
                </div>

                <button type="submit">
                    Submit
                </button>
            </form>
        </div>
    )
}

const Workers = () => {
    const [workers, setWorkers] = useState([])
    const [qualifications, setQualifications] = useState([])
    const [showCreateWorkerForm, setShowCreateWorkerForm] = useState(false)

    const loadWorkers = () => {
        getWorkers().then(setWorkers)
    }

    useEffect(() => {
        loadWorkers()
        getQualifications().then(setQualifications)
    }, [])

    const active = LocationID('workers', workers, 'name')

    return (
        <div style={pageStyle}>
            <h1>
                Company Workers
            </h1>

            <button onClick={() => setShowCreateWorkerForm(!showCreateWorkerForm)}>
                {showCreateWorkerForm ? 'Cancel' : 'Create New Worker'}
            </button>

            {showCreateWorkerForm ? (
                <CreateWorkerForm
                    qualifications={qualifications}
                    onWorkerCreated={() => {
                        loadWorkers()
                        setShowCreateWorkerForm(false)
                    }}
                />
            ) : null}

            <ClickList active={active} list={workers} item={Worker} path='/workers' id='name' />
        </div>
    )
}

export default Workers